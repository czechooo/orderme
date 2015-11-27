module.exports = function (grunt) {
    'use strict';

    var webAppDir = 'src/main/webapp/';
    var scssDir = webAppDir + 'WEB-INF/scss/';
    var jsDir  = webAppDir + 'WEB-INF/js/';
    var assDir = webAppDir + 'WEB-INF/assets/';
    var topDir = webAppDir + 'WEB-INF/top';

    var dstDir         = webAppDir + 'static/';
    var dstCssDir      = dstDir    + 'css/';
    var dstJsDir       = dstDir    + 'js/';
    var dstAssDir      = dstDir    + 'assets/';

    var vendorFiles = [
        'bower_components/angular/angular.js',
        'bower_components/angular-animate/angular-animate.js',
        'bower_components/angular-sanitize/angular-sanitize.js',
        'bower_components/angular-route/angular-route.js'
    ];

    var cssVendorFiles = [
        'bower_components/angular-growl-v2/build/angular-growl.min.css'
    ];

    var jsVendorFiles = [
        'bower_components/angular-growl-v2/build/angular-growl.min.js'
    ];

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        project: {
            webAppDir: ['src/main/webapp'],
            webInfDir: ['<%= project.webAppDir%>/WEB-INF'],
            staticDir: ['<%= project.webAppDir%>/static']
        },
        clean: [ dstDir ],
        copy: {
            main: {
                files: [
                    { expand: true, cwd: scssDir, src: ['images/*'],   dest: dstCssDir },
                    { expand: true, cwd: './',    src: cssVendorFiles, dest: dstCssDir, flatten: true },
                    { expand: true, cwd: './',    src: jsVendorFiles, dest: dstJsDir, flatten: true },
                    { expand: true, cwd: assDir, src: ['**/*'], dest: dstAssDir },
                    { expand: true, cwd: topDir, src: ['**'], dest: dstDir }
                ]
            }
        },
        uglify: {
            dev: {
                options: {
                    compress: {},
                    beautify: true,
                    mangle: false,
                    report: 'min'
                },
                src: vendorFiles,
                dest: dstJsDir + 'vendor.js'
            },
            prod: {
                options: {
                    compress: {},
                    beautify: false,
                    mangle: false,
                    report: 'min'
                },
                src: vendorFiles,
                dest: dstJsDir + 'vendor.js'
            }
        },
        watchify: {
            dev: {
                src: './' + jsDir + '**/*js',
                dest: dstJsDir + 'app.js',
                options: {
                    watch: false,
                    keepalive: false,
                    debug: true
                }
            },
            devon: {
                src: './' + jsDir + '**/*js',
                dest: dstJsDir + 'app.js',
                options: {
                    watch: false,
                    keepalive: true,
                    debug: true
                }
            },
            prod: {
                src: './' + jsDir + '**/*.js',
                dest: dstJsDir + 'app.js',
                options: {
                    watch: false,
                    keepalive: false,
                    debug: false
                }
            },
            options: {
                // defaults options used in b.bundle(opts)
                detectGlobals: true,
                insertGlobals: false,
                ignoreMissing: false,
                debug: true,
                standalone: false
            }
        },
        watch: {
            dev : {
                files: [scssDir + '**/*.scss'],
                tasks: ['sass:dev']
            }
        },
        sass: {
            options: {
                includePaths: ['bower_components/bootstrap-sass/assets/stylesheets', 'bower_components/angular-material/']
            },
            dev: {
                options: {
                    outputStyle: 'expand',
                    sourceMap: true
                },
                files: {
                    // Need to use <%= %> syntax cause variable on its own wouldn't make it
                    '<%= project.staticDir %>/css/app.css': scssDir + 'app.scss'
                }
            },
            prod: {
                options: {
                    outputStyle: 'compressed',
                    sourceMap: false
                },
                files: {
                    '<%= project.staticDir %>/css/app.css': scssDir + 'app.scss'
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-watch');

    grunt.loadNpmTasks('grunt-watchify');
    grunt.loadNpmTasks('grunt-browserify');
    grunt.loadNpmTasks('grunt-sass');


    grunt.registerTask('dev',  ['clean', 'watchify:dev', 'uglify:dev', 'sass:dev', 'copy']);
    grunt.registerTask('prod', ['clean', 'watchify:prod', 'uglify:prod', 'sass:prod', 'copy']);

    // Helper tasks to watch for changes in javascript or css
    grunt.registerTask('css:watch', ['watch:dev']);
    grunt.registerTask('js:watch', ['watchify:devon']);

    grunt.registerTask('js:dev',   ['watchify:dev']);
    grunt.registerTask('js:prod',  ['watchify:prod']);
    grunt.registerTask('css:dev',  ['sass:dev']);
    grunt.registerTask('css:prod', ['sass:prod']);

};