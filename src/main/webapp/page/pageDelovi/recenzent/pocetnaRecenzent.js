naucnaCentrala.controller('pocetnaRecenzent', [
		'$rootScope',
		'$scope',
		'$state',
		'$http',
		function($rootScope, $scope, $state, $http) {

			$scope.taskovi = [];

			$scope.logout = function() {
				$rootScope.korisnik = "";
				$state.go("login");
			}
			$scope.prikaziCasopise = function() {
				$http.get(
						"http://localhost:8080/process/recTasks/"
								+ $rootScope.korisnik.email).then(
						function(response) {
							$scope.taskovi = response.data;

						}, function(response) {
						});
			};

			$scope.izaberiCasopis = function(x) {
				$state.go("recenzentPregledRada", {
					"rad" : x.rad,
					"task" : x.taskId,
					"processInstanceId" : x.processId
				});
			};

			$scope.prikaziCasopise();
		} ]);