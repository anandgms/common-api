package edu.anand.web.security;

import jakarta.servlet.http.HttpServletRequest;
import java.security.cert.X509Certificate;
import java.util.*;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.x509.X509PrincipalExtractor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CutomX509PrincipalExtractor implements X509PrincipalExtractor {

  private static final List<String> DN_FIELDS = List.of("UID", "CN", "EMAILADDRESS");

  // SAN types
  private static final int UPN = 0; // otherName: Custom name formats (e.g., UPN).
  private static final int RFC822_NAME = 1; // rfc822Name: Email addresses.
  private static final int DNS = 2; // dNSName: Domain names (e.g., example.com).
  private static final int X400 = 3; // x400Address: X.400 address (rarely used).
  private static final int DN = 4; // directoryName: Distinguished Name (DN).
  private static final int EDI = 5; // ediPartyName: EDI party name.
  private static final int URI = 6; // uniformResourceIdentifier: URI/URL
  private static final int IP_ADDRESS = 7; // iPAddress: IPv4 or IPv6, stored as OCTET STRING.
  private static final int OID = 8; // registeredID: Object Identifier (OID).

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

    throw new UsernameNotFoundException("No username found in certificate");
  }

  public static X509Certificate getX509Certificate(HttpServletRequest request) {

    X509Certificate[] certs =
        (X509Certificate[]) request.getAttribute("jakarta.servlet.request.X509Certificate");

    if (certs != null && certs.length > 0) {
      // The first certificate in the array is the client's actual certificate
      return certs[0];
    }

    return null;
  }

  private static String extractFromDN(X509Certificate cert) {
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

  private static String extractFromSAN(X509Certificate cert) {
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
