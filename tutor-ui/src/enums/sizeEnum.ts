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

export enum SizeEnum {
  DEFAULT = 'default',
  SMALL = 'small',
  LARGE = 'large',
}

export enum SizeNumberEnum {
  DEFAULT = 48,
  SMALL = 16,
  LARGE = 64,
}

export const sizeMap: Map<SizeEnum, SizeNumberEnum> = (() => {
  const map = new Map<SizeEnum, SizeNumberEnum>();
  map.set(SizeEnum.DEFAULT, SizeNumberEnum.DEFAULT);
  map.set(SizeEnum.SMALL, SizeNumberEnum.SMALL);
  map.set(SizeEnum.LARGE, SizeNumberEnum.LARGE);
  return map;
})();
