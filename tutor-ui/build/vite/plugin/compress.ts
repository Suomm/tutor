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

/**
 * Used to package and output gzip. Note that this does not work properly in Vite, the specific reason is still being investigated
 * https://github.com/anncwb/vite-plugin-compression
 */
import type { Plugin } from 'vite';
import compressPlugin from 'vite-plugin-compression';

export function configCompressPlugin(
  compress: 'gzip' | 'brotli' | 'none',
  deleteOriginFile = false,
): Plugin | Plugin[] {
  const compressList = compress.split(',');

  const plugins: Plugin[] = [];

  if (compressList.includes('gzip')) {
    plugins.push(
      compressPlugin({
        ext: '.gz',
        deleteOriginFile,
      }),
    );
  }

  if (compressList.includes('brotli')) {
    plugins.push(
      compressPlugin({
        ext: '.br',
        algorithm: 'brotliCompress',
        deleteOriginFile,
      }),
    );
  }
  return plugins;
}
