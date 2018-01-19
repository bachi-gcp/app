package com.biarca.app.ws.web.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {
  public static void main(String[] args) {

    switch (args.length) {
      case 1:
        showPasswordHash(args[0]);
        break;
      default:
        usage();
        return;
    }
  }

  protected static void showPasswordHash(String password) {
    int i = 0;
    while (i < 5) {
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      String hashedPassword = passwordEncoder.encode(password);
      System.out.println(hashedPassword);
      i++;
    }
  }

  protected static void usage() {
    System.out.println(
        "Usage: java -classpath spring-boot-starter-security-<VERSION>.jar com.biarca.app.ws.web.security.PasswordEncoderGenerator");
  }
}
