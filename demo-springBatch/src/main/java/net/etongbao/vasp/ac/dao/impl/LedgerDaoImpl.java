package net.etongbao.vasp.ac.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.etongbao.vasp.ac.dao.LedgerDao;
import net.etongbao.vasp.ac.pojo.Ledger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

/**
 * ledger数据操作类
 * 
 * @author Fu Wei
 * 
 */

@Repository
public class LedgerDaoImpl implements LedgerDao {

	private static final String SAVE_SQL = "insert into ledger_temp (rcv_dt, mbr_nm, chk_nbr, chk_dt, pymt_typ, dpst_amt, pymt_amt, comments) values(?,?,?,?,?,?,?,?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(final Ledger item) {
		int i=jdbcTemplate.update(SAVE_SQL, new PreparedStatementSetter() {
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setDate(1, new java.sql.Date(item.getReceiptDate().getTime()));
				stmt.setString(2, item.getMemberName());
				stmt.setString(3, item.getCheckNumber());
				stmt.setDate(4, new java.sql.Date(item.getCheckDate().getTime()));
				stmt.setString(5, item.getPaymentType());
				stmt.setDouble(6, item.getDepositAmount());
				stmt.setDouble(7, item.getPaymentAmount());
				stmt.setString(8, item.getComments());
			}
		});
		System.out.println("执行响应码："+i);
	}

}
