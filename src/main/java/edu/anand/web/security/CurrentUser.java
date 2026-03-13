package edu.anand.web.security;

import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.HexFormat;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/** Wrapper class to get username, roles, certifcation and other details from Spring Security */
public final class CurrentUser {

  private CurrentUser() {}

  public static Authentication authentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  public static String username() {

    Authentication auth = authentication();
    if (auth == null) {
      return null;
    }

    Object principal = auth.getPrincipal();

    if (principal instanceof UserDetails user) {
      return user.getUsername();
    }

    return auth.getName();
  }

  public static Collection<String> roles() {

    Authentication auth = authentication();

    if (auth == null) {
      return null;
    }

    return auth.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList());
  }

  public static X509Certificate certificate() {

    Authentication auth = authentication();

    if (auth == null) {
      return null;
    }

    Object creds = auth.getCredentials();

    if (creds instanceof X509Certificate cert) {
      return cert;
    }

    return null;
  }

  public static String certificateFingerprint() {

    try {

      X509Certificate cert = certificate();

      if (cert == null) {
        return null;
      }

      MessageDigest digest = MessageDigest.getInstance("SHA-256");

      byte[] hash = digest.digest(cert.getEncoded());

      return HexFormat.of().formatHex(hash);

    } catch (Exception e) {
      throw new RuntimeException("Unable to compute certificate fingerprint", e);
    }
  }
}
