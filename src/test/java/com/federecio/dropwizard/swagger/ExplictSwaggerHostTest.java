/**
 * Copyright (C) 2014 Federico Recio
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.federecio.dropwizard.swagger;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.federecio.dropwizard.junitrunner.DropwizardJunitRunner;
import com.federecio.dropwizard.junitrunner.DropwizardTestConfig;
import com.jayway.restassured.RestAssured;

/**
 * @author Federico Recio
 */
@RunWith(DropwizardJunitRunner.class)
@DropwizardTestConfig(applicationClass = TestApplicationExplicitSwaggerHost.class, yamlFile = "/test-simple-with-path-2.yaml")
public class ExplictSwaggerHostTest {

    @BeforeClass
    public static void setPort() {
        RestAssured.port = 44447;
    }

    @Test
    public void resourceIsAvailable() throws Exception {
        RestAssured.expect().statusCode(HttpStatus.OK_200).when().get("/api/test.json");
    }

    @Test
    public void swaggerIsAvailable() throws Exception {
        RestAssured.expect().statusCode(HttpStatus.OK_200).when().get("/api/api-docs");
        RestAssured.expect().statusCode(HttpStatus.OK_200).when().get("/api/api-docs/test");
        RestAssured.expect().statusCode(HttpStatus.OK_200).when().get("/api/swagger");
    }
}
