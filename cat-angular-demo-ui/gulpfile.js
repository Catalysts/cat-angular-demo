var gulp = require('gulp');
gulp.util = require('gulp-util');

var _ = require('lodash');

var browserSync = require('browser-sync');
var url = require('url');
var proxyMiddleware = require('proxy-middleware');

var proxyFunction = proxyMiddleware(url.parse('http://localhost:8080'));

gulp.task('browser-sync', function () {
    var baseDirs = [
        './src/main/resources/static',
        '../../cat-angular/dist'
    ];

    browserSync({
        open: false,
        server: {
            baseDir: baseDirs,
            routes: {
                "/webjars/cat-angular/0.0.51": "../../cat-angular/dist"
            },
            middleware: function (req, res, next) {
                if (req.url.match(/cat-angular.js$/)) {
                    gulp.util.log('Serving cat-angular');
                    next();
                } else
                if (req.url.match(/^\/webjars/i)) {
                    proxyFunction(req, res, next);
                } else if (req.url.match(/(css|js|html|map)$/i)) {
                    gulp.util.log('Serving file: ' + req.url);
                    next();
                } else {
                    proxyFunction(req, res, next);
                }
            }
        },
        files: _.map(baseDirs, function(baseDir) {
            return baseDir + '/**';
        })
    });
});