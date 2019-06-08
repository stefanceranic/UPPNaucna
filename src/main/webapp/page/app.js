var naucnaCentrala= angular.module('naucnaCentrala',['ui.router','ngFileSaver'])

naucnaCentrala.config(['$urlRouterProvider','$stateProvider',function($urlRouterProvider,$stateProvider) {
    $urlRouterProvider.otherwise('/login');
    $stateProvider
        .state('register',{
            url:'/register',
            controller: 'registerCtrl',
            templateUrl: 'pageDelovi/reglog/register.html'
        })
        .state('login',{
            url:'/login',
            controller: 'loginCtrl',
            templateUrl: 'pageDelovi/reglog/login.html'
        })
        .state('biranjeCasopisa',{
            url:'/biranjeCasopisa',
            controller: 'biranjeCasopisaCtrl',
            templateUrl: 'pageDelovi/autor/biranjeCasopisa.html'
        })
        .state('prijavaRada',{
            url:'/prijavaRada',
            params: {
                processId: null
            },
            controller: 'prijavaRada',
            templateUrl: 'pageDelovi/autor/prijavaRada.html'
        })
        .state('pocetnaAutor',{
            url:'/pocetnaAutor',
            controller: 'pocetnaAutor',
            templateUrl: 'pageDelovi/autor/pocetnaAutor.html'
        })
        .state('pocetnaRecenzent',{
            url:'/pocetnaRecenzent',
            controller: 'pocetnaRecenzent',
            templateUrl: 'pageDelovi/recenzent/pocetnaRecenzent.html'
        })
        .state('pocetnaUrednik',{
            url:'/pocetnaUrednik',
            controller: 'pocetnaUrednik',
            templateUrl: 'pageDelovi/urednik/pocetnaUrednik.html'
        })
        .state('odabirCasopisa',{
            url:'/odabirCasopisa',
            controller: 'odabirCasopisa',
            templateUrl: 'pageDelovi/autor/odabirCasopisa.html'
        })
        .state('urednikDownload',{
            url:'/urednikDownload',
            controller: 'urednikDownload',
            templateUrl: 'pageDelovi/urednik/urednikDownload.html'
        })
        .state('radoviZaOdobravanje',{
            url:'/radoviZaOdobravanje',
            controller: 'radoviZaOdobravanje',
            templateUrl: 'pageDelovi/urednik/radoviZaOdobravanje.html'
        })
        .state('odabirRecenzenata',{
            url:'/odabirRecenzenata',
            controller: 'odabirRecenzenata',
            templateUrl: 'pageDelovi/urednik/odabirRecenzenata.html'
        })
        .state('urednikPregledRada',{
            url:'/urednikPregledRada/:rad/:task/:processInstanceId',
            controller: 'urednikPregledRada',
            templateUrl: 'pageDelovi/urednik/urednikPregledRada.html'
        })
        .state('recenzentPregledRada',{
            url:'/recenzentPregledRada/:rad/:task/:processInstanceId',
            controller: 'recenzentPregledRada',
            templateUrl: 'pageDelovi/recenzent/recenzentPregledRada.html'
        })
        .state('urednikPregledFormataRada',{
            url:'/urednikPregledRada/:rad/:processInstanceId',
            controller: 'urednikPregledFormataRada',
            templateUrl: 'pageDelovi/urednik/urednikPregledFormataRada.html'
        })
        .state('odabirRadaZaIspravku',{
            url:'/odabirRadaZaIspravku',
            controller: 'odabirRadaZaIspravku',
            templateUrl: 'pageDelovi/autor/odabirRadaZaIspravku.html'
        })
        .state('odaberiRecenzente',{
            url:'/odaberiRecenzente/:rad/:task/:processInstanceId',
            controller: 'odaberiRecenzente',
            templateUrl: 'pageDelovi/urednik/odaberiRecenzente.html'
        })
        .state('urednikPregledRecenzija',{
            url:'/urednikPregledRecenzija/:rad/:task/:processInstanceId',
            controller: 'urednikPregledRecenzija',
            templateUrl: 'pageDelovi/urednik/urednikPregledRecenzija.html'
        })
        .state('urednikOdabirRadaZaRecenziranje',{
            url:'/urednikOdabirRadaZaRecenziranje',
            controller: 'urednikOdabirRadaZaRecenziranje',
            templateUrl: 'pageDelovi/urednik/urednikOdabirRadaZaRecenziranje.html'
        })
        

}]);