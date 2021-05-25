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

package com.splunk.opentelemetry.profiler;

import static com.splunk.opentelemetry.profiler.Configuration.CONFIG_KEY_ENABLE_PROFILER;

import com.google.auto.service.AutoService;
import io.opentelemetry.instrumentation.api.config.Config;
import io.opentelemetry.javaagent.spi.ComponentInstaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AutoService(ComponentInstaller.class)
public class JfrActivator implements ComponentInstaller {

  private static final Logger logger = LoggerFactory.getLogger(JfrActivator.class.getName());

  @Override
  public void afterByteBuddyAgent(Config config) {
    if (!config.getBooleanProperty(CONFIG_KEY_ENABLE_PROFILER, false)) {
      logger.debug("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
      logger.debug("xxxxxxxxx  JFR PROFILER DISABLED!  xxxxxxxxx");
      logger.debug("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
      return;
    }
    logger.info("JFR profiler is active.");
  }
}
