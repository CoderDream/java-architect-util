package com.keepsoft.microservice.entity;

import com.keepsoft.microservice.dto.OntologyDto;
import lombok.Data;

import java.util.List;

@Data
//@NodeEntity(label = "table")
public class Table {

  //    @Id
//    @GeneratedValue
  private Long id;

  /////@Property(name = "name")
  private String name;

  ////@Property(name = "desc")
  private String desc;

  //@Property(name = "createTime")
  private String createTime;

  //@Property(name = "updateTime")
  private String updateTime;

  ////@Transient
  private Long dataSourceId;


  //////////@Transient
  private Integer ontologyType;

  //@Transient
  private Long ontologyId;

  //@Transient
  private List<Column> relationList;

  //@Transient
  private List<Entity> relationEntityList;

  //@Transient
  private List<Relationship> relationRelationshipList;

  //@Transient
  private DataSource dataSource;

  //@Transient
  private List<OntologyDto> ontologyList;

  //@Transient
  private Integer index;

  //@Transient
  private String columnName;
}
