package com.opchaves.kommonei.activities;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ActivityMapper {

  @Mapping(target = "id", expression = "java(a.id.toHexString())")
  @Mapping(target = "userId", expression = "java(a.id.toHexString())")
  ActivityDTO toDTO(Activity a);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "handledAt", expression = "java(a == null ? java.time.LocalDateTime.now() : a.handledAt)")
  Activity toEntity(ActivityDTO a);
}
