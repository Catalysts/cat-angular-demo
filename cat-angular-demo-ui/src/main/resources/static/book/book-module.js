(function () {

    var app = angular.module('cat.angular.demo.book.BookModule', []);

    app.config(['catViewServiceProvider', function (catViewServiceProvider) {
        // We are going to replace the default containers for both, the list and detail views.
        // As soon as a templateUrl (for list or details) is provided, the default template is not used anymore.

        catViewServiceProvider.listAndDetailView('', 'Book', {
            list: {
                templateUrl: 'book/book-list-container.tpl.html'
            },
            details: {
                templateUrl: 'book/book-details-container.tpl.html'
            }
        });
    }]);

})();