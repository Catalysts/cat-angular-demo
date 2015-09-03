var app = angular.module('cat.angular.demo.TranslationModule', []);

var translations = {
    'cc.catalysts.general.new': 'Neu',
    'cc.catalysts.general.delete': 'Löschen',
    'cc.catalysts.general.edit': 'Bearbeiten',
    'cc.catalysts.general.cancel': 'Abbrechen',
    'cc.catalysts.general.save': 'Speichern',
    'cc.catalysts.cat-paginated.pagination.previous': 'Vorige',
    'cc.catalysts.cat-paginated.pagination.next': 'Nächste',
    'cc.catalysts.cat-paginated.pagination.first': 'Erste',
    'cc.catalysts.cat-paginated.pagination.last': 'Letzte'
};

function cat18nServiceDecorator($delegate, $q) {
    $delegate.translate = function (key) {
        return $q.when(translations[key]);
    };

    $delegate.canTranslate = function (key) {
        return $q.when(!!translations[key]);
    };

    return $delegate;
}

function cat18nServiceDecoratorProvider($provide) {
    $provide.decorator('catI18nService', ['$delegate', '$q', cat18nServiceDecorator]);
}

app.config(['$provide', cat18nServiceDecoratorProvider]);