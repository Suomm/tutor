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

import { BasicArrow } from '/@/components/Basic';

export default () => {
  return (props: Recordable) => {
    if (!props.expandable) {
      if (props.needIndentSpaced) {
        return <span class="ant-table-row-expand-icon ant-table-row-spaced" />;
      } else {
        return <span />;
      }
    }
    return (
      <BasicArrow
        style="margin-right: 8px"
        iconStyle="margin-top: -2px;"
        onClick={(e: Event) => {
          props.onExpand(props.record, e);
        }}
        expand={props.expanded}
      />
    );
  };
};
