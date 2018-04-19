package kr.ac.jejunu;

import java.sql.*;

public class ProductDao {
    private final JdbcContext jdbcContext;

    public ProductDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public Product get(Long id) throws SQLException {
        StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
        return jdbcContext.JdbcContextForGet(statementStrategy);
    }

    public Long insert(Product product) throws SQLException {
        StatementStrategy statementStrategy = new InsertUserStatementStrategy(product);
        return jdbcContext.JdbcContextForInsert(statementStrategy);
    }

    public void update(Product product) throws SQLException {
        StatementStrategy statementStrategy = new UpdateUserStatementStrategy(product);
        jdbcContext.JpdcContextForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteUserStatementStrategy(id);
        jdbcContext.JpdcContextForUpdate(statementStrategy);
    }
}