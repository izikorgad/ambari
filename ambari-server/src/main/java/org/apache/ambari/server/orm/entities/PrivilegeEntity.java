/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing privileges and
 * limitations under the License.
 */

package org.apache.ambari.server.orm.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Represents an admin privilege.
 */
@Table(name = "adminprivilege")
@Entity
@TableGenerator(name = "privilege_id_generator",
    table = "ambari_sequences", pkColumnName = "sequence_name", valueColumnName = "value"
    , pkColumnValue = "privilege_id_seq"
    , initialValue = 1
    , allocationSize = 1
)
public class PrivilegeEntity {

  /**
   * The privilege id.
   */
  @Id
  @Column(name = "privilege_id")
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "privilege_id_generator")
  private Integer id;

  /**
   * The permission.
   */
  @ManyToOne
  @JoinColumns({
      @JoinColumn(name = "permission_id", referencedColumnName = "permission_id", nullable = false),
  })
  private PermissionEntity permission;

  /**
   * The resource.
   */
  @ManyToOne
  @JoinColumns({
      @JoinColumn(name = "resource_id", referencedColumnName = "resource_id", nullable = false),
  })
  private ResourceEntity resource;

  /**
   * The principal.
   */
  @ManyToOne
  @JoinColumns({
      @JoinColumn(name = "principal_id", referencedColumnName = "principal_id", nullable = false),
  })
  private PrincipalEntity principal;


  // ----- PrivilegeEntity ---------------------------------------------------

  /**
   * Get the privilege id.
   *
   * @return the privilege id.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Set the privilege id.
   *
   * @param id  the type id.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Get the associated permission entity.
   *
   * @return the permission entity
   */
  public PermissionEntity getPermission() {
    return permission;
  }

  /**
   * Set the permission entity.
   *
   * @param permission  the permission entity
   */
  public void setPermission(PermissionEntity permission) {
    this.permission = permission;
  }

  /**
   * Get the associated resource entity.
   *
   * @return the resource entity
   */
  public ResourceEntity getResource() {
    return resource;
  }

  /**
   * Set the resource entity.
   *
   * @param resource  the resource entity
   */
  public void setResource(ResourceEntity resource) {
    this.resource = resource;
  }

  /**
   * Get the associated principal entity.
   *
   * @return the principal entity
   */
  public PrincipalEntity getPrincipal() {
    return principal;
  }

  /**
   * Set the principal entity.
   *
   * @param principal  the principal entity
   */
  public void setPrincipal(PrincipalEntity principal) {
    this.principal = principal;
  }


  // ----- Object overrides --------------------------------------------------

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PrivilegeEntity that = (PrivilegeEntity) o;

    if (!id.equals(that.id)) return false;
    if (permission != null ? !permission.equals(that.permission) : that.permission != null) return false;
    if (principal != null ? !principal.equals(that.principal) : that.principal != null) return false;
    if (resource != null ? !resource.equals(that.resource) : that.resource != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (permission != null ? permission.hashCode() : 0);
    result = 31 * result + (resource != null ? resource.hashCode() : 0);
    result = 31 * result + (principal != null ? principal.hashCode() : 0);
    return result;
  }
}
