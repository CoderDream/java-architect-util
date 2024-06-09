const mysql = require('mysql');

// 连接信息
const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '123456',
    database: 'nodejs_book'
});

// https://www.cnblogs.com/penghq/p/16787691.html
// mysql8.0.27之后无法指定加密方式创建用户或者修改密码
// mysql> ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';
//1396 - Operation ALTER USER failed for 'root'@'localhost'
//mysql> ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
//Query OK, 0 rows affected (0.01 sec)
//
//mysql> FLUSH PRIVILEGES;
//Query OK, 0 rows affected (0.00 sec)

// 建立连接
/// connection.connect();
connection.connect(function (err) {
    if (err) {
        console.error('error connecting: ' + err.stack);
        return;
    }

    console.log('connected as id ' + connection.threadId);
});

// 执行查询
connection.query('SELECT * FROM t_user',
    function (error, results, fields) {
        if (error) {
            throw error;
        }

        // 打印查询结果
        console.log('SELECT result is: ', results);
    });

// 插入数据
var data = { user_id: 2, username: 'waylau' };
connection.query('INSERT INTO t_user SET ?', data,
    function (error, results, fields) {
        if (error) {
            throw error;
        }

        // 打印查询结果
        console.log('INSERT result is: ', results);
    });

// 执行查询
connection.query('SELECT * FROM t_user',
    function (error, results, fields) {
        if (error) {
            throw error;
        }

        // 打印查询结果
        console.log('SELECT result is: ', results);
    });

// 更新数据
connection.query('UPDATE t_user SET username = ? WHERE user_id = ?', ['Way Lau', 2],
    function (error, results, fields) {
        if (error) {
            throw error;
        }

        // 打印查询结果
        console.log('UPDATE result is: ', results);
    });

// 执行查询
connection.query('SELECT * FROM t_user',
    function (error, results, fields) {
        if (error) {
            throw error;
        }

        // 打印查询结果
        console.log('SELECT result is: ', results);
    });


// 更新数据
connection.query('UPDATE t_user SET username = ? WHERE user_id = ?', ['Way Lau', 2],
    function (error, results, fields) {
        if (error) {
            throw error;
        }

        // 打印查询结果
        console.log('UPDATE result is: ', results);
    });

// 执行查询
connection.query('SELECT * FROM t_user',
    function (error, results, fields) {
        if (error) {
            throw error;
        }

        // 打印查询结果
        console.log('SELECT result is: ', results);
    });

// 删除数据
connection.query('DELETE FROM t_user WHERE user_id = ?', 2,
    function (error, results, fields) {
        if (error) {
            throw error;
        }

        // 打印查询结果
        console.log('DELETE result is: ', results);
    });

// 执行查询
connection.query('SELECT * FROM t_user',
    function (error, results, fields) {
        if (error) {
            throw error;
        }

        // 打印查询结果
        console.log('SELECT result is: ', results);
    });

// 关闭连接
///connection.end();
///connection.destroy();
connection.end(function (err) {
    if (err) {
        console.error('error end: ' + err.stack);
        return;
    }

    console.log('end connection');
});
