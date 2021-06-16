/*
 * Copyright Splunk Inc.
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

package com.splunk.opentelemetry.khttp;

import static java.util.Collections.singletonList;

import com.google.auto.service.AutoService;
import io.opentelemetry.javaagent.extension.instrumentation.InstrumentationModule;
import io.opentelemetry.javaagent.extension.instrumentation.TypeInstrumentation;
import java.util.Arrays;
import java.util.List;

@AutoService(InstrumentationModule.class)
public class KHttpInstrumentationModule extends InstrumentationModule {

  public KHttpInstrumentationModule() {
    super("khttp");
  }

  @Override
  public List<TypeInstrumentation> typeInstrumentations() {
    return singletonList(new KHttpInstrumentation());
  }

  @Override
  public List<String> getMuzzleHelperClassNames() {
    return Arrays.asList(
        "com.splunk.opentelemetry.khttp.KHttpTracer",
        "com.splunk.opentelemetry.khttp.KHttpHeadersInjectAdapter",
        "com.splunk.opentelemetry.khttp.RequestWrapper");
  }
}
