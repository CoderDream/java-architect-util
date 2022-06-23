module.exports = {
    root: true,
    env: {
        node: true
    },
    extends: [
        "plugin:vue/essential",
        "eslint:recommended",
        "plugin:prettier/recommended"
    ],
    parserOptions: {
        // parser: '@babel/eslint-parser',
    },
    rules: {
        quotes: [0, "double"],
        indent: ["error", 4], // 4个空格缩进
        // 去掉函数()前面的空格
        "space-before-function-paren": "off",
        "no-console": process.env.NODE_ENV === "production" ? "warn" : "off",
        "no-debugger": process.env.NODE_ENV === "production" ? "warn" : "off",
        // 关闭驼峰命名规则
        "vue/multi-word-component-names": 0
    }
}