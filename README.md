# Spring for test automation engineer - Phonebook


## Description

Currently, the project can be run in CLI (`cli` spring profile) mode (the initial task of the project) and in WebMVC
(`webmvc` spring profile) mode (the final task of the project)


### Core

`com.phonebook.core` - Contains interface and class definitions that are used in both versions of the app. The package
is loaded as part of the `CliConfig.class` or `WebmvcConfig.class` (depending on the profile). The app starts from
`com.phonebook.PhoneBookMain` where it creates a `ConfigurableApplicationContext` from the `ApplicationConfig` class or
the `application-config.xml` resource file (both versions have the same configuration).


### Config

The main configuration of the app is located in `com.phonebook.config.ApplicationConfig`. This configuration imports
the following two configurations `CliConfig` (with component scan for `com.phonebook.core` and `com.phonebook.env.cli`)
and `WebmvcConfig`(with component scan for `com.phonebook.core` and `com.phonebook.env.webmvc`), however only the 
configuration according to the active spring profile (default: `webmvc`)


### Env

#### CLI

`com.phonebook.env.cli` - Contains class definitions that are used in the CLI version of the app. The entrypoint is
defined in `com.phonebook.env.cli.entrypoint.EntrypointCli` class.

#### WebMVC

`com.phonebook.env.webmvc` - Contains class definitions that are used in the WebMVC version of the app. The entrypoint
is defined in `com.phonebook.env.webmvc.entrypoint.EntrypointWebmvc` class. 
