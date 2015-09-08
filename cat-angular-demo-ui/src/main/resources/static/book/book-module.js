(function() {

    var app = angular.module('cat.angular.demo.book.BookModule',[

    ]);

    app.config(['catViewServiceProvider', function (catViewServiceProvider) {
        catViewServiceProvider.listAndDetailView('', 'Book', {});
    }]);

})();