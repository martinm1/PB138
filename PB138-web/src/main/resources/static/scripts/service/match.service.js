'use strict';

angular.module('sparta')
    .factory('MatchService', function ($http) {
        return {
            getAllMatches: function () {
                return $http.get('/match/getAllMatches').then(function (response) {
                    return response.data;
                })
            },
            getAllMatchesAgainstTeam: function (name) {
                return $http.get('/match/getAllMatchesAgainstTeam', {
                    params: {
                        name: name
                    }
                }).then(function (response) {
                    return response.data;
                });
            },
            getAllHomeMatches: function () {
                return $http.get('/match/getAllHomeMatches').then(function (response) {
                    return response.data;
                })
            },
            getAllAwayMatches: function () {
                return $http.get('/match/getAllAwayMatches').then(function (response) {
                    return response.data;
                })
            },
            countTotalSpectators: function () {
                return $http.get('/match/countTotalSpectators').then(function (response) {
                    return response.data;
                })
            },
            countTotalShots: function () {
                return $http.get('/match/countTotalShots').then(function (response) {
                    return response.data;
                })
            }
        }
    });