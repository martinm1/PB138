'use strict';

angular.module('sparta')
    .controller('HomeController', function (MatchService) {
        var vm = this;
        function init() {
            MatchService.getAllMatches().then(function (data) {
                console.log("All matches",data);

            });
            MatchService.countTotalSpectators().then(function (data){
                console.log("Total Spectators",data);
            });
        }

        init();
        return vm;
    });