/*
 * Copyright 2021-2022 the original author and authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import path from 'path';
import fs from 'fs-extra';
import inquirer from 'inquirer';
import chalk from 'chalk';
import pkg from '../../../package.json';

async function generateIcon() {
  const dir = path.resolve(process.cwd(), 'node_modules/@iconify/json');

  const raw = await fs.readJSON(path.join(dir, 'collections.json'));

  const collections = Object.entries(raw).map(([id, v]) => ({
    ...(v as any),
    id,
  }));

  const choices = collections.map((item) => ({ key: item.id, value: item.id, name: item.name }));

  inquirer
    .prompt([
      {
        type: 'list',
        name: 'useType',
        choices: [
          { key: 'local', value: 'local', name: 'Local' },
          { key: 'onLine', value: 'onLine', name: 'OnLine' },
        ],
        message: 'How to use icons?',
      },
      {
        type: 'list',
        name: 'iconSet',
        choices: choices,
        message: 'Select the icon set that needs to be generated?',
      },
      {
        type: 'input',
        name: 'output',
        message: 'Select the icon set that needs to be generated?',
        default: 'src/components/Icon/data',
      },
    ])
    .then(async (answers) => {
      const { iconSet, output, useType } = answers;
      const outputDir = path.resolve(process.cwd(), output);
      fs.ensureDir(outputDir);
      const genCollections = collections.filter((item) => [iconSet].includes(item.id));
      const prefixSet: string[] = [];
      for (const info of genCollections) {
        const data = await fs.readJSON(path.join(dir, 'json', `${info.id}.json`));
        if (data) {
          const { prefix } = data;
          const isLocal = useType === 'local';
          const icons = Object.keys(data.icons).map(
            (item) => `${isLocal ? prefix + ':' : ''}${item}`,
          );

          await fs.writeFileSync(
            path.join(output, `icons.data.ts`),
            `export default ${isLocal ? JSON.stringify(icons) : JSON.stringify({ prefix, icons })}`,
          );
          prefixSet.push(prefix);
        }
      }
      fs.emptyDir(path.join(process.cwd(), 'node_modules/.vite'));
      console.log(
        `✨ ${chalk.cyan(`[${pkg.name}]`)}` + ' - Icon generated successfully:' + `[${prefixSet}]`,
      );
    });
}

generateIcon();
