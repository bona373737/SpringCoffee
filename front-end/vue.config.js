module.exports = {
  devServer: {
      port: 8081,   // FE 포트

      proxy: {
          '/': {
              target : 'http://127.0.0.1:8080',
              changeOrigin: true
          }
      }
  }
};