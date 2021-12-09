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

import {
  defineAsyncComponent,
  // FunctionalComponent, CSSProperties
} from 'vue';
import { Spin } from 'ant-design-vue';
import { noop } from '/@/utils/index';

// const Loading: FunctionalComponent<{ size: 'small' | 'default' | 'large' }> = (props) => {
//   const style: CSSProperties = {
//     position: 'absolute',
//     display: 'flex',
//     justifyContent: 'center',
//     alignItems: 'center',
//   };
//   return (
//     <div style={style}>
//       <Spin spinning={true} size={props.size} />
//     </div>
//   );
// };

interface Options {
  size?: 'default' | 'small' | 'large';
  delay?: number;
  timeout?: number;
  loading?: boolean;
  retry?: boolean;
}

export function createAsyncComponent(loader: Fn, options: Options = {}) {
  const { size = 'small', delay = 100, timeout = 30000, loading = false, retry = true } = options;
  return defineAsyncComponent({
    loader,
    loadingComponent: loading ? <Spin spinning={true} size={size} /> : undefined,
    // The error component will be displayed if a timeout is
    // provided and exceeded. Default: Infinity.
    // TODO
    timeout,
    // errorComponent
    // Defining if component is suspensible. Default: true.
    // suspensible: false,
    delay,
    /**
     *
     * @param {*} error Error message object
     * @param {*} retry A function that indicating whether the async component should retry when the loader promise rejects
     * @param {*} fail  End of failure
     * @param {*} attempts Maximum allowed retries number
     */
    onError: !retry
      ? noop
      : (error, retry, fail, attempts) => {
          if (error.message.match(/fetch/) && attempts <= 3) {
            // retry on fetch errors, 3 max attempts
            retry();
          } else {
            // Note that retry/fail are like resolve/reject of a promise:
            // one of them must be called for the error handling to continue.
            fail();
          }
        },
  });
}
