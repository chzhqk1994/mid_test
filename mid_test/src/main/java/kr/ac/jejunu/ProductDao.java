package kr.ac.jejunu;

import java.sql.*;

public class ProductDao {
    private final JdbcContext jdbcContext;

    public ProductDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public Product get(Long id) throws SQLException {
        Long id1 = id;
        StatementStrategy statementStrategy = connection -> {

            String sql = "select * from product where id = ?";
            Object[] params = new Object[]{id};
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++)
                preparedStatement.setObject(i + 1, params[i]);

            return preparedStatement;
        };
        return jdbcContext.JdbcContextForGet(statementStrategy);
    }

    public Long insert(Product product) throws SQLException {
        Product product1 = product;
        StatementStrategy statementStrategy = connection -> {
            String sql = "INSERT INTO product (title, price) VALUES (?, ?)";
            Object[] params = new Object[]{product.getTitle(), product.getPrice()};
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++)
                preparedStatement.setObject(i + 1, params[i]);

            return preparedStatement;
        };
        return jdbcContext.JdbcContextForInsert(statementStrategy);
    }

    public void update(Product product) throws SQLException {
        Product product1 = product;
        StatementStrategy statementStrategy =
                connection -> {
                    String sql = "UPDATE product SET title=?, price=? WHERE id=?";
                    Object[] params = new Object[]{product.getTitle(), product.getPrice(), product.getId()};
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    for (int i = 0; i < params.length; i++)
                        preparedStatement.setObject(i + 1, params[i]);

                    return preparedStatement;
                };
        jdbcContext.JpdcContextForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException {
        Long id1 = id;
        StatementStrategy statementStrategy = connection -> {
            String sql = "DELETE FROM product WHERE id=?";
            Object[] params = new Object[]{id};
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++)
                preparedStatement.setObject(i + 1, params[i]);

            return preparedStatement;
        };
        jdbcContext.JpdcContextForUpdate(statementStrategy);
    }
}