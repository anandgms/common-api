package edu.anand.security;

import java.security.cert.X509Certificate;
import java.util.*;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import org.springframework.security.web.authentication.preauth.x509.X509PrincipalExtractor;
import org.springframework.util.StringUtils;

public class CustomX509PrincipalExtractor implements X509PrincipalExtractor {

  private static final List<String> DN_FIELDS = List.of("UID", "CN", "EMAILADDRESS");

  // [0] otherName: Custom name formats (e.g., UPN).
  private static final int UPN = 0;
  // [1] rfc822Name: Email addresses.
  private static final int RFC822_NAME = 1;
  // [2] dNSName: Domain names (e.g., example.com).
  private static final int DNS = 2;
  // [3] x400Address: X.400 address (rarely used).
  private static final int X400 = 3;
  // [4] directoryName: Distinguished Name (DN).
  private static final int DN = 4;
  // [5] ediPartyName: EDI party name.
  private static final int EDI = 5;
  // [6] uniformResourceIdentifier: URI/URL (e.g., https://example.com).
  private static final int URI = 6;
  // [7] iPAddress: IP address (IPv4 or IPv6, stored as OCTET STRING).
  private static final int IP_ADDRESS = 7;
  // [8] registeredID: Object Identifier (OID).
  private static final int OID = 8;

  @Override
  public Object extractPrincipal(X509Certificate cert) {

    String username = extractFromDN(cert);

    if (StringUtils.hasText(username)) {
      return username;
    }

    username = extractFromSAN(cert);

    if (StringUtils.hasText(username)) {
      return username;
    }

    throw new RuntimeException("No username found in certificate");
  }

  private String extractFromDN(X509Certificate cert) {

    try {

      LdapName ldapDN = new LdapName(cert.getSubjectX500Principal().getName());

      Map<String, String> dnMap = new HashMap<>();

      for (Rdn rdn : ldapDN.getRdns()) {
        dnMap.put(rdn.getType().toUpperCase(), rdn.getValue().toString());
      }

      for (String field : DN_FIELDS) {
        if (dnMap.containsKey(field)) {
          return dnMap.get(field);
        }
      }

    } catch (Exception ignored) {
    }

    return null;
  }

  private String extractFromSAN(X509Certificate cert) {

    try {

      Collection<List<?>> san = cert.getSubjectAlternativeNames();

      if (san == null) {
        return null;
      }

      for (List<?> entry : san) {

        Integer type = (Integer) entry.get(0);
        Object value = entry.get(1);

        switch (type) {
          case RFC822_NAME, DNS, URI -> value.toString();
        }
      }

    } catch (Exception ignored) {
    }

    return null;
  }
}
