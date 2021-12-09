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

import { Ref, watchEffect, ref } from 'vue';

interface IntersectionObserverProps {
  target: Ref<Element | null | undefined>;
  root?: Ref<any>;
  onIntersect: IntersectionObserverCallback;
  rootMargin?: string;
  threshold?: number;
}

export function useIntersectionObserver({
  target,
  root,
  onIntersect,
  rootMargin = '0px',
  threshold = 0.1,
}: IntersectionObserverProps) {
  let cleanup = () => {};
  const observer: Ref<Nullable<IntersectionObserver>> = ref(null);
  const stopEffect = watchEffect(() => {
    cleanup();

    observer.value = new IntersectionObserver(onIntersect, {
      root: root ? root.value : null,
      rootMargin,
      threshold,
    });

    const current = target.value;

    current && observer.value.observe(current);

    cleanup = () => {
      if (observer.value) {
        observer.value.disconnect();
        target.value && observer.value.unobserve(target.value);
      }
    };
  });

  return {
    observer,
    stop: () => {
      cleanup();
      stopEffect();
    },
  };
}
