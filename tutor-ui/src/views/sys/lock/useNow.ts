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

import { dateUtil } from '/@/utils/dateUtil';
import { reactive, toRefs } from 'vue';
import { useLocaleStore } from '/@/store/modules/locale';
import { tryOnMounted, tryOnUnmounted } from '@vueuse/core';

export function useNow(immediate = true) {
  const localeStore = useLocaleStore();
  const localData = dateUtil.localeData(localeStore.getLocale);
  let timer: IntervalHandle;

  const state = reactive({
    year: 0,
    month: 0,
    week: '',
    day: 0,
    hour: '',
    minute: '',
    second: 0,
    meridiem: '',
  });

  const update = () => {
    const now = dateUtil();

    const h = now.format('HH');
    const m = now.format('mm');
    const s = now.get('s');

    state.year = now.get('y');
    state.month = now.get('M') + 1;
    state.week = localData.weekdays()[now.day()];
    state.day = now.get('D');
    state.hour = h;
    state.minute = m;
    state.second = s;

    state.meridiem = localData.meridiem(Number(h), Number(h), true);
  };

  function start() {
    update();
    clearInterval(timer);
    timer = setInterval(() => update(), 1000);
  }

  function stop() {
    clearInterval(timer);
  }

  tryOnMounted(() => {
    immediate && start();
  });

  tryOnUnmounted(() => {
    stop();
  });

  return {
    ...toRefs(state),
    start,
    stop,
  };
}
