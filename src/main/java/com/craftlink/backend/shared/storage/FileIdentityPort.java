package com.craftlink.backend.shared.storage;

public interface FileIdentityPort {

  String generateKey(String prefix, String fileName);

  String generateKey(String prefix, String fileName, String userId);
}
