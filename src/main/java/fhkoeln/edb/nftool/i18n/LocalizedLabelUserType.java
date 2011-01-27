package fhkoeln.edb.nftool.i18n;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import fhkoeln.edb.nftool.service.InternationalizationService;

public class LocalizedLabelUserType implements UserType, Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LocalizedLabelUserType.class);
	@Autowired
	private InternationalizationService internationalizationService;

	// @Value("externalContext.locale")
	private Locale locale = Locale.GERMAN;

	private static final int[] SQL_TYPES = { Types.BIGINT };

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@Override
	public Class returnedClass() {
		return String.class;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {

		// Long labelId = (Long) Hibernate.BIG_INTEGER.nullSafeGet(rs, names);
		// return owner.getClass() + " val";//
		// LocalizedLabelUtil.getText(labelId,
		// LocaleUtil.currentLocale());
		long textId = rs.getLong(names[0]);
		if (rs.wasNull())
			return null;
		return internationalizationService
				.getText(owner.getClass().getSimpleName(), textId, locale);

	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException {

		Long labelId = 1L;// LocalizedLabelUtil.getId((String) value,
							// LocaleUtil.currentLocale());
		Hibernate.LONG.nullSafeSet(st, labelId, index);
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y)
			return true;
		else if (x == null || y == null)
			return false;
		else
			return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return null;
	}

	public InternationalizationService getinternationalizationService() {
		return internationalizationService;
	}

	public void setinternationalizationService(
			InternationalizationService internationalizationService) {
		this.internationalizationService = internationalizationService;
	}
}
