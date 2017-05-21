'use strict';

angular.module('sparta')
    .factory('TeamService', function ($http) {
        return {
            findTeam: function (name) {
                return $http.get('/team/findTeam', {
                    params: {
                        name: name
                    }
                }).then(function (response) {
                    return response.data;
                });
            },
            findAllTeams: function () {
                return $http.get('/team/findAllTeams').then(function (response) {
                    return response.data;
                })
            }
        }
    });