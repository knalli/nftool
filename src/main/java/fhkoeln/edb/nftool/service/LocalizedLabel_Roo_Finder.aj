// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fhkoeln.edb.nftool.service;

import fhkoeln.edb.nftool.service.LocalizedLabel;
import java.lang.String;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect LocalizedLabel_Roo_Finder {
    
    public static TypedQuery<LocalizedLabel> LocalizedLabel.findLocalizedLabelsByEntityUriAndAttributeNameAndLocale(String entityUri, String attributeName, Locale locale) {
        if (entityUri == null || entityUri.length() == 0) throw new IllegalArgumentException("The entityUri argument is required");
        if (attributeName == null || attributeName.length() == 0) throw new IllegalArgumentException("The attributeName argument is required");
        if (locale == null) throw new IllegalArgumentException("The locale argument is required");
        EntityManager em = LocalizedLabel.entityManager();
        TypedQuery<LocalizedLabel> q = em.createQuery("SELECT LocalizedLabel FROM LocalizedLabel AS localizedlabel WHERE localizedlabel.entityUri = :entityUri AND localizedlabel.attributeName = :attributeName AND localizedlabel.locale = :locale", LocalizedLabel.class);
        q.setParameter("entityUri", entityUri);
        q.setParameter("attributeName", attributeName);
        q.setParameter("locale", locale);
        return q;
    }
    
}
