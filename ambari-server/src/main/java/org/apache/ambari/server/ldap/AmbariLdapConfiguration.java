/*
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
package org.apache.ambari.server.ldap;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.assistedinject.Assisted;

/**
 * This class is an immutable representation of all the LDAP related configurationMap entries.
 */
@Singleton
public class AmbariLdapConfiguration {

  private static final Logger LOGGER = LoggerFactory.getLogger(AmbariLdapConfiguration.class);

  /**
   * Constants representing supported LDAP related property names
   */
  public enum LdapConfigProperty {
    LDAP_CONFIGURED("ambari.ldap.configured"),
    AUTOMATIC_ATTRIBUTE_DETECTION("ambari.ldap.automatic.attribute.detection"),
    USE_SSL("ambari.ldap.usessl"),
    LDAP_SERVER_HOST("ambari.ldap.server.host"),
    LDAP_SERVER_PORT("ambari.ldap.server.port"),
    LDAP_TRUSTSTORE("ambari.ldap.truststore"),
    LDAP_TRUSTSTORE_TYPE("ambari.ldap.truststore.type"),
    LDAP_TRUSTSTORE_PATH("ambari.ldap.truststore.path"),
    LDAP_TRUSTSTORE_PASSWORD("ambari.ldap.truststore.password"),
    BASE_DN("ambari.ldap.bind.dn"),
    REFERRAL("ambari.ldap.referral"),
    PAGINATION_ENABLED("ambari.ldap.pagination.enabled"),

    BIND_ANONIMOUSLY("ambari.ldap.bindanonymously"),
    MANAGER_DN("ambari.ldap.managerdn"),
    MANAGER_PASSWORD("ambari.ldap.managerpassword"),
    USER_OBJECT_CLASS("ambari.ldap.user.object.class"),
    USER_NAME_ATTRIBUTE("ambari.ldap.user.name.attribute"),
    USER_NAME_FORCE_LOWERCASE("ambari.ldap.username.force.lowercase"),
    USER_SEARCH_BASE("ambari.ldap.user.search.base"),
    SYNC_USER_MEMBER_REPLACE_PATTERN("ambari.ldap.sync.user.member.replacepattern"),
    SYNC_USER_MEMBER_FILTER("ambari.ldap.sync.user.member_filter"),

    ADMIN_GROUP_MAPPING_RULES ("ambari.ldap.admin.group.mappingrules"),
    GROUP_OBJECT_CLASS("ambari.ldap.group.object.class"),
    GROUP_NAME_ATTRIBUTE("ambari.ldap.group.name.attribute"),
    GROUP_MEMBER_ATTRIBUTE("ambari.ldap.group.member.attribute"),
    GROUP_SEARCH_BASE("ambari.ldap.group.search.base"),
    SYNC_GROUP_MEMBER_REPLACE_PATTERN("ambari.ldap.sync.group.member.replacepattern"),
    SYNC_GROUP_MEMBER_FILTER("ambari.ldap.sync.group.member_filter"),
    DN_ATTRIBUTE("authentication.ldap.dnAttribute"),

    TEST_USER_NAME("ambari.ldap.test.user.name"),
    TEST_USER_PASSWORD("ambari.ldap.test.user.password");

    private String propertyName;

    LdapConfigProperty(String propertyName) {
      this.propertyName = propertyName;
    }

    public String propertyName() {
      return this.propertyName;
    }
  }

  private final Map<String, Object> configurationMap;

  private Object configurationValue(LdapConfigProperty ldapConfigProperty) {
    Object value = null;
    if (configurationMap.containsKey(ldapConfigProperty.propertyName)) {
      value = configurationMap.get(ldapConfigProperty.propertyName);
    } else {
      LOGGER.warn("Ldap configuration property [{}] hasn't been set", ldapConfigProperty.propertyName());
    }

    return value;
  }

  @Inject
  public AmbariLdapConfiguration(@Assisted Map<String, Object> configuration) {
    this.configurationMap = configuration;
  }


  public String ldapServerHost() {
    return (String) configurationValue(LdapConfigProperty.LDAP_SERVER_HOST);
  }

  public int ldapServerPort() {
    return Integer.valueOf((String) configurationValue(LdapConfigProperty.LDAP_SERVER_PORT));
  }

  public boolean useSSL() {
    return Boolean.valueOf((String) configurationValue(LdapConfigProperty.USE_SSL));
  }

  public boolean bindAnonimously() {
    return Boolean.valueOf((String) configurationValue(LdapConfigProperty.BIND_ANONIMOUSLY));
  }

  public String managerDn() {
    return (String) configurationValue(LdapConfigProperty.MANAGER_DN);
  }

  public String managerPassword() {
    return (String) configurationValue(LdapConfigProperty.MANAGER_PASSWORD);
  }

  public boolean automaticAttributeDetection() {
    return Boolean.valueOf((String) configurationValue(LdapConfigProperty.AUTOMATIC_ATTRIBUTE_DETECTION));
  }

  public String baseDn() {
    return (String) configurationValue(LdapConfigProperty.BASE_DN);
  }

  public String userObjectClass() {
    return (String) configurationValue(LdapConfigProperty.USER_OBJECT_CLASS);
  }

  public String userNameAttribute() {
    return (String) configurationValue(LdapConfigProperty.USER_NAME_ATTRIBUTE);
  }

  public String userSearchBase() {
    return (String) configurationValue(LdapConfigProperty.USER_SEARCH_BASE);
  }

  public String groupObjectClass() {
    return (String) configurationValue(LdapConfigProperty.GROUP_OBJECT_CLASS);
  }

  public String groupNameAttribute() {
    return (String) configurationValue(LdapConfigProperty.GROUP_NAME_ATTRIBUTE);
  }

  public String groupMemberAttribute() {
    return (String) configurationValue(LdapConfigProperty.GROUP_MEMBER_ATTRIBUTE);
  }

  public String groupSearchBase() {
    return (String) configurationValue(LdapConfigProperty.GROUP_SEARCH_BASE);
  }

}
