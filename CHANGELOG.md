# Changelog

All notable changes to this project will be documented in this file.

## [2.0.2] - 2026-11-21

### Changed

- Incidents without an execution id (for example, raised during process instance version migrations) are no longer logged; they are passed straight to the default incident handler.
- Upgrades to **CibSeven 3.0.0** engine (from 2.2.0). The library uses only the engine API, so the starter artifact renames in CibSeven 3.0.0 do not affect it. The jar is binary compatible with CibSeven 2.2.0 engines: it references the same 19 engine members whether compiled against 2.2.0 or 3.0.0.
- Upgrades to **Spring Boot 4.2.0** (from 4.0.6), which brings Spring Framework 7.1.0.
- Updates the build plugins to their latest releases: maven-compiler-plugin 3.16.0, maven-deploy-plugin 3.2.0, maven-enforcer-plugin 3.7.0, maven-surefire-plugin and maven-failsafe-plugin 3.6.1, sonar-maven-plugin 5.9.0.7382 and central-publishing-maven-plugin 0.12.0. Removes the unused `nexus-staging-plugin.version` property.

## [2.0.1] - 2026-06-01

### Changed

- Switches from Camunda 7 Community Edition engine to **CibSeven 2.2.0** engine.
- Upgrades to **Spring Boot 4.0.6** (from 4.0.4).

## [2.0.0] - 2026-03-26

### Changed

- Switches from Camunda 7 Community Edition engine to **CibSeven 2.1.0** engine. CibSeven is an actively maintained community fork of Camunda 7 with the same database schema and REST API.
- Upgrades to **Spring Boot 4.0.4** (from 3.5.6).

## [1.0.4]

### Changed

- Updates Spring Boot to 3.5.6.
- Updates Camunda to 7.24.0.

## [1.0.3]

### Changed

- Initial open source version.
- Adds Apache License 2.0.

## [1.0.2]

### Changed

- Updates Spring Boot to 3.4.0.

## [1.0.1]

### Changed

- Updates Camunda to 7.22.0.

## [1.0.0]

### Added

- Initial version with incident logging for `FAILED_JOB` and `EXTERNAL_TASK` handler types.

[2.0.1]: https://github.com/opentmf/camunda7-incident-logger/compare/v2.0.0...v2.0.1
[2.0.0]: https://github.com/opentmf/camunda7-incident-logger/compare/v1.0.4...v2.0.0
[1.0.4]: https://github.com/opentmf/camunda7-incident-logger/compare/v1.0.3...v1.0.4
[1.0.3]: https://github.com/opentmf/camunda7-incident-logger/compare/v1.0.2...v1.0.3
[1.0.2]: https://github.com/opentmf/camunda7-incident-logger/compare/v1.0.1...v1.0.2
[1.0.1]: https://github.com/opentmf/camunda7-incident-logger/compare/v1.0.0...v1.0.1
[1.0.0]: https://github.com/opentmf/camunda7-incident-logger/releases/tag/v1.0.0
