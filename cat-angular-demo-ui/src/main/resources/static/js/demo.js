angular.module('demo', ['cat', 'cat.template'])
    .config(function(catViewServiceProvider) {
        catViewServiceProvider.listAndDetailView('', 'Book');
    })
    .run(function () {
        console.log('i am angular');
    });
