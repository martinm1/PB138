'use strict';

angular.module('sparta')
    .controller('HomeController', function (MatchService) {
        var vm = this;
        vm.matches = [];
        vm.totalSpectators = 0;
        vm.totalShots = 0;
        vm.refreshAll = function(){
            MatchService.getAllMatches().then(function (data){
                console.log("All matches",data);
                vm.matches = data;
            });
        };
        vm.refreshHome = function(){
            MatchService.getAllHomeMatches().then(function (data){
                console.log("Home matches",data);
                vm.matches = data;
            });
        };
        vm.refreshAway = function(){
            MatchService.getAllAwayMatches().then(function (data){
                console.log("Away matches",data);
                vm.matches = data;
            });
        };
        function init() {
            MatchService.getAllMatches().then(function (data) {
                console.log("All matches",data);
                vm.matches = data;
            });
            MatchService.countTotalSpectators().then(function (data){
                console.log("Total Spectators",data);
                vm.totalSpectators = data;
            });
            MatchService.countTotalShots().then(function (data){
                console.log("Total Shots",data);
                vm.totalShots = data;
            });
        }

        init();
        return vm;
    });