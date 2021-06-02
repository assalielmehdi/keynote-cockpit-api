package io.assalielmehdi.keynote.security;

public enum Authority {
  USER,
  ADMIN;

  @Override
  public String toString() {
    if (this.equals(USER)) {
      return "USER";
    } else {
      return "ADMIN";
    }
  }

}
