angular.module('demo', ['cat', 'cat.template'])
    .config(function(catViewServiceProvider) {
        window.cat.util.defaultModelResolver = function() {
            return function(data) {
                _.extend(this, data);
            };
        };

        catViewServiceProvider.listAndDetailView('', 'Book', {});
        catViewServiceProvider.listAndDetailView('', 'Manufacturer');
    })
    .run(function () {
        console.log('i am angular');
    });