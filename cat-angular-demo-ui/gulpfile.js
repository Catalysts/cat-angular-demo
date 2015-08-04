var gulp = require('gulp');

var browserSync = require('browser-sync');
var url = require('url');
var proxyMiddleware = require('proxy-middleware');

var proxyFunction = proxyMiddleware(url.parse('http://localhost:8080'));

gulp.task('browser-sync', function () {
    var baseDir = './src/main/resources/static';

    browserSync({
        open: false,
        server: {
            baseDir: baseDir,
            middleware: function (req, res, next) {
                if (req.url.match(/^\/webjars/i)) {
                    proxyFunction(req, res, next);
                } else if (req.url.match(/(css|js|html)$/i)) {
                    next();
                } else {
                    proxyFunction(req, res, next);
                }
            }
        },
        files: [baseDir + '/**']
    });
});