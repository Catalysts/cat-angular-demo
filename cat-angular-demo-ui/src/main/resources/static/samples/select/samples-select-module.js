(function () {
    var app = angular.module('cat.angular.demo.samples.select.SelectModule', [
        'cat.angular.demo.samples.select.Controller'
    ]);

    // register the endpoint (countriesSelect)
    app.config(['catApiServiceProvider', function (catApiServiceProvider) {

        function Country(data) {
            angular.extend(this, data);     //maps all the data properties
        }

        // an endpoint, just for demonstration
        // we could also use the existing endpoint from Module:  'cat.demo.country.CountryModule'
        catApiServiceProvider.endpoint('countriesSelect', {
            url: 'countries',
            model: Country
        });
    }]);

    // countriesConfigProvider
    app.config(['catSelectConfigServiceProvider', function (catSelectConfigServiceProvider) {

        // We are going to register the named config.
        // By doing so, we are able to use the same config several times.
        catSelectConfigServiceProvider.config('samplesConfig', {
            endpoint: 'countriesSelect',
            'ui-select2': {     // include some ui-select2 specific settings
                allowClear: true
            }
        });
    }]);
})();