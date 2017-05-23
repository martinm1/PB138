'use strict';

angular.module('sparta').config(function ($stateProvider, $urlRouterProvider) {

    $stateProvider.state('home', {
        url: '/home',
        views: {
            'main': {
                templateUrl: 'html/home.html',
                controller: 'HomeController as homeController'
            }
        }
    }).state('match', {
        url: '/match/{matchId}',
        views: {
            'main': {
                templateUrl: 'html/match.html',
                controller: 'MatchController as matchController'
            }
        }
    });

    $urlRouterProvider.otherwise('home');
});