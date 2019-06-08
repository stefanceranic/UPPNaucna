naucnaCentrala.controller('urednikOdabirRadaZaRecenziranje', ['$rootScope','$scope','$state','$http', function ($rootScope, $scope,$state,$http) {


    $scope.taskovi = [];

    $scope.prikaziCasopise = function() {
        $http.get("http://localhost:8080/process/pregledRecenzija/"+$rootScope.korisnik.email).then(
            function(response) {
                $scope.taskovi = response.data;

            }, function(response) {
            });
    };

    $scope.odaberiCasopis = function(x) {
            	$state.go("urednikPregledRecenzija", {"rad": x.rad, "task": x.taskId, "processInstanceId": x.processId});
    };
    $scope.prikaziCasopise();

}]);