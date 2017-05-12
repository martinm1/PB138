'use strict';

angular.module('sparta')
    .controller('HomeController', function (TeamService) {
        var vm = this;


        function init() {
            TeamService.getAllMatches().then(function (data) {
                console.log("All matches",data);

            });
            TeamService.countTotalSpectators().then(function (data){
                console.log("Total Spectators",data);
            });
        }

        init();
        return vm;
    });