package net.etongbao.vasp.ac.batch.writer;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.etongbao.vasp.ac.pojo.Ledger;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
 
/**
 * ledger行的映射类
 * @author Administrator
 *
 */
@Component("ledgerRowMapper")
public class LedgerRowMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ledger ledger = new Ledger();
		ledger.setId(rs.getInt("id"));
		ledger.setReceiptDate(rs.getDate("rcv_dt"));
		ledger.setMemberName(rs.getString("mbr_nm"));
		ledger.setCheckNumber(rs.getString("chk_nbr"));
		ledger.setCheckDate(rs.getDate("chk_dt"));
		ledger.setPaymentType(rs.getString("pymt_typ"));
		ledger.setDepositAmount(rs.getDouble("dpst_amt"));
		ledger.setPaymentAmount(rs.getDouble("pymt_amt"));
		ledger.setComments(rs.getString("comments"));
		return ledger;
	}
}
