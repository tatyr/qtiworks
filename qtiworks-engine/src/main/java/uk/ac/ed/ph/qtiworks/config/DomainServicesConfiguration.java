/* Copyright (c) 2012, University of Edinburgh.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice, this
 *   list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 * * Neither the name of the University of Edinburgh nor the names of its
 *   contributors may be used to endorse or promote products derived from this
 *   software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 * This software is derived from (and contains code from) QTItools and MathAssessEngine.
 * QTItools is (c) 2008, University of Southampton.
 * MathAssessEngine is (c) 2010, University of Edinburgh.
 */
package uk.ac.ed.ph.qtiworks.config;

import uk.ac.ed.ph.qtiworks.domain.services.QtiWorksSettings;
import uk.ac.ed.ph.qtiworks.domain.services.SystemEmailService;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configures beans and services at the domain layer
 *
 * @author David McKain
 */
@Configuration
@ComponentScan(basePackages={"uk.ac.ed.ph.qtiworks.domain.services"})
public class DomainServicesConfiguration {

    @Resource
    private QtiWorksSettings qtiWorksSettings;

    @Bean
    public LocalValidatorFactoryBean jsr303Validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public MailSender mailSender() {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(qtiWorksSettings.getEmailSmtpHost());
        return mailSender;
    }

    @Bean
    public SystemEmailService systemEmailService() {
        final SystemEmailService emailService = new SystemEmailService();
        return emailService;
    }

    @Resource(name="extraJpaProperties")
    private Properties extraJpaProperties;

    @Bean(destroyMethod="close")
    public DataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(qtiWorksSettings.getJdbcDriverClassName());
        dataSource.setUrl(qtiWorksSettings.getJdbcUrl());
        dataSource.setUsername(qtiWorksSettings.getJdbcUsername());
        dataSource.setPassword(qtiWorksSettings.getJdbcPassword());
        return dataSource;
    }

    @Bean
    public Properties jpaProperties() {
        final Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", qtiWorksSettings.getHibernateDialect());
        jpaProperties.putAll(extraJpaProperties);
        return jpaProperties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceXmlLocation("classpath:/META-INF/persistence.xml");
        emf.setDataSource(dataSource());
        emf.setJpaProperties(jpaProperties());
        return emf;
    }
}