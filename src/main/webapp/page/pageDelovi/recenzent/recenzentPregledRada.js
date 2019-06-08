naucnaCentrala.controller('recenzentPregledRada', [
		'$scope',
		'$state',
		'$http',
		'$stateParams',
        'FileSaver',
        'Blob',
		function($scope, $state, $http, $stateParams, FileSaver, Blob) {

            $scope.rad = $stateParams.rad;
            var task = $stateParams.task;
            var processInstanceId = $stateParams.processInstanceId;
            $scope.model = {};

            $scope.pokreniProccess = function() {
                $http.get("http://localhost:8080/process/getFF/"+processInstanceId)
                    .then(
                        function(response) {
                            $scope.properties = response.data;

                        }, function(response) {
                        });
            }
            $scope.pokreniProccess();

//            $scope.proveraRada = function (x) {
//                $http.post("http://localhost:8080/recenzent/prihvatiRad/" + task, x)
//                    .then(function (response) {
//                        if (x)
//                            $state.go("urednikPregledFormataRada", {
//                                "rad": rad,
//                                "processInstanceId": processInstanceId
//                            });
//                        else
//                            $state.go("pocetnaUrednik");
//                    }, function (response) {
//                    });
//
//            }

            $scope.preuzmiRad = function(y) {

                $http.post("http://localhost:8080/rad/download", y, {
                    responseType : 'arraybuffer'
                }).then(function(response) {
                    var myData = new Blob([ response.data ], {
                        type : 'application/pdf;charset=utf-8'
                    });
                    FileSaver.saveAs(myData, 'nesto.pdf');
                }, function(response) {

                });

            };

            $scope.potvrdiRecenziju=function(){
                var o = [];
                for (var m in $scope.model) {
                    console.log(m + "  -  " + $scope.model[m]);
                    o.push({ "fieldId": m, "fieldValue": $scope.model[m] });
                }
                $http.post("http://localhost:8080/recenzent/komentarisi/"+$scope.properties.taskId, o)
                    .then(function (response) {
                            alert("Uspesno ste obavili recenziju")
                            $state.go("pocetnaRecenzent");
                        }
                        , function (response) {
                            alert("Neuspeh");
                        });

            }
        }]);
