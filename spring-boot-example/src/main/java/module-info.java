/**
 * Copyright 2019 Deere & Company
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

// Unpublished Work (c) 2019 Deere & Company

open module com.deere.isg.worktracker.spring.boot.example {
    requires  com.deere.isg.worktracker.spring.boot;
    requires spring.boot.starter.web;
    requires spring.boot.starter.security;
    requires spring.boot.autoconfigure;
    requires spring.security.config;
    requires spring.context;
    requires spring.web;
    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires tomcat.embed.core;
    requires slf4j.api;
    requires spring.security.core;
    exports com.deere.example.spring;
}