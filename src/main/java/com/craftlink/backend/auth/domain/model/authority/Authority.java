package com.craftlink.backend.auth.domain.model.authority;

import com.craftlink.backend.auth.domain.model.authority.vo.AuthorityCode;
import com.craftlink.backend.auth.domain.model.authority.vo.AuthorityId;
import com.craftlink.backend.auth.domain.model.authority.vo.AuthorityName;
import com.craftlink.backend.shared.domain.model.vo.AggregateRoot;
import lombok.Getter;

@Getter
public final class Authority extends AggregateRoot {

  private final AuthorityId id;
  private final AuthorityName name;
  private final AuthorityCode code;

  private Authority(AuthorityId id, AuthorityName name, AuthorityCode code) {
    this.id = id;
    this.name = name;
    this.code = code;
  }

  public static Authority create(AuthorityId id, AuthorityName name, AuthorityCode code) {
    return new Authority(id, name, code);
  }

  public static Authority rehydrate(AuthorityId id, AuthorityName name, AuthorityCode code) {
    return new Authority(id, name, code);
  }
}
