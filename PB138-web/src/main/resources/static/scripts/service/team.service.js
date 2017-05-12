'use strict';

angular.module('webdac')
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