# Metrics

> :construction: This feature is experimental - exported metric data and configuration properties may change.

The Splunk Distribution of OpenTelemetry Java agent gathers basic application metrics. We
use [Micrometer](https://micrometer.io/)
and [Micrometer SignalFx registry](https://micrometer.io/docs/registry/signalFx)
to gather and export metrics to either [SignalFx SmartAgent](https://github.com/signalfx/signalfx-agent/)
or the [Splunk distribution of OpenTelemetry Collector](https://github.com/signalfx/splunk-otel-collector).

## Default metric tags

The following dimensions are automatically added to all metrics exported by the agent:

| Tag name                 | Tag value |
| ------------------------ | --------- |
| `deployment.environment` | The value of the `deployment.environment` resource attribute, if present.
| `service`                | The value of the `service.name` resource attribute.
| `runtime`                | The value of the `process.runtime.name` resource attribute, e.g. `OpenJDK Runtime Environment`.
| `process.pid`            | The Java process identifier (PID).

## Supported libraries

The following metrics are currently gathered by the agent:

* [JVM metrics](#jvm)
* [Apache DBCP2 connection pool metrics](#apache-dbcp2-connection-pool)

### JVM

We use the [built-in Micrometer JVM metrics extension](https://micrometer.io/docs/ref/jvm)
to register JVM measurements.

#### Classloader metrics

| Metric name                    | Description |
| ------------------------------ | ----------- |
| `runtime.jvm.classes.loaded`   | The number of loaded classes.
| `runtime.jvm.classes.unloaded` | The total number of unloaded classes since process start.

#### GC metrics

| Metric name                            | Description |
| -------------------------------------- | ----------- |
| `runtime.jvm.gc.max.data.size`         | Max size of long-lived heap memory pool.
| `runtime.jvm.gc.live.data.size`        | Size of long-lived heap memory pool after reclamation.
| `runtime.jvm.gc.memory.allocated`      | Incremented for an increase in the size of the (young) heap memory pool after one GC to before the next.
| `runtime.jvm.gc.memory.promoted`       | Count of positive increases in the size of the old generation memory pool before GC to after GC.
| `runtime.jvm.gc.concurrent.phase.time` | Time spent in concurrent phase.
| `runtime.jvm.gc.pause`                 | Time spent in GC pause.

#### Memory metrics

| Metric name                    | Description |
| ------------------------------ | ----------- |
| `runtime.jvm.memory.used`      | The amount of used memory.
| `runtime.jvm.memory.committed` | The amount of memory in bytes that is committed for the Java virtual machine to use.
| `runtime.jvm.memory.max`       | The maximum amount of memory in bytes that can be used for memory management.

All memory pool metrics have the following tags:

| Tag name | Tag value |
| -------- | --------- |
| `id`     | Name of the memory pool, e.g. `Perm Gen`.
| `area`   | Either `heap` or `nonheap`.

#### Thread metrics

| Metric name                  | Description |
| ---------------------------- | ----------- |
| `runtime.jvm.threads.peak`   | The peak live thread count since the Java virtual machine started or peak was reset.
| `runtime.jvm.threads.daemon` | The current number of live daemon threads.
| `runtime.jvm.threads.live`   | The current number of live threads including both daemon and non-daemon threads.
| `runtime.jvm.threads.states` | The current number of threads per `state` (metric tag).

### Apache DBCP2 connection pool

| Metric name                    | Description |
| ------------------------------ | ----------- |
| `db.pool.connections`          | The number of open connections.
| `db.pool.connections.active`   | The number of open connections that are currently in use.
| `db.pool.connections.idle`     | The number of open connections that are currently idle.
| `db.pool.connections.idle.min` | The minimum number of idle open connections allowed.
| `db.pool.connections.idle.max` | The maximum number of idle open connections allowed.
| `db.pool.connections.max`      | The maximum number of open connections allowed.

All Apache DBCP2 metrics have the following tags:

| Tag name    | Tag value |
| ----------- | --------- |
| `pool.type` | `dbcp2`
| `pool.name` | The name of the connection pool: Spring bean name if Spring is used, the JMX object name otherwise.
