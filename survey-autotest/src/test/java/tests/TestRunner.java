package tests;

import common.Utils;

public class TestRunner {
    static int total = 0, passed = 0, failed = 0;

    static void run(String name, Runnable test) {
        total++;
        try {
            test.run();
            passed++;
            System.out.println("  [PASS] " + name);
        } catch (AssertionError | Exception e) {
            failed++;
            System.out.println("  [FAIL] " + name + " - " + e.getMessage().split("\n")[0]);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("===========================");
        System.out.println("Survey System Auto Test");
        System.out.println("===========================\n");

        // === Register Tests ===
        System.out.println("--- Register ---");
        RegisterTest reg = new RegisterTest();
        run("TC-REG-01 success", reg::registerSuccess);
        run("TC-REG-02 empty name", reg::registerFailEmptyName);
        run("TC-REG-03 empty account", reg::registerFailEmptyAccount);
        run("TC-REG-04 existing account", reg::registerFailExist);
        run("TC-REG-05 pwd mismatch", reg::registerFailPwdMismatch);

        // === Login Tests ===
        System.out.println("\n--- Login ---");
        LoginTest login = new LoginTest();
        run("TC-LOGIN-01 success", login::loginSuccess);
        run("TC-LOGIN-02 empty username", login::loginFailEmptyUsername);
        run("TC-LOGIN-03 empty password", login::loginFailEmptyPassword);
        run("TC-LOGIN-04 wrong credentials", login::loginFailWrong);
        run("TC-LOGIN-05 correct user wrong pwd", login::loginFailWrongPwd);
        run("TC-LOGIN-06 wrong user correct pwd", login::loginFailWrongUser);

        // === Home Tests ===
        System.out.println("\n--- Home ---");
        HomeTest home = new HomeTest();
        run("TC-HOME-01 no login redirect", home::noLoginHome);
        run("TC-HOME-02 home elements", home::homeElements);
        run("TC-HOME-03 create survey", home::createSurvey);
        run("TC-HOME-04 create exam", home::createExam);

        // === Project Tests ===
        System.out.println("\n--- Project ---");
        ProjectTest proj = new ProjectTest();
        run("TC-PROJECT-01 list blank BUG", proj::projectListBug);
        run("TC-PROJECT-02 no login redirect", proj::noLoginProject);

        // === Repo Tests ===
        System.out.println("\n--- Repo ---");
        RepoTest repo = new RepoTest();
        run("TC-REPO-01 list page", repo::repoListPage);
        run("TC-REPO-02 create", repo::createRepo);
        run("TC-REPO-03 export BUG", repo::exportBug);

        // === Template Tests ===
        System.out.println("\n--- Template ---");
        TemplateTest tmpl = new TemplateTest();
        run("TC-TEMP-01 list page", tmpl::templateListPage);
        run("TC-TEMP-02 search BUG", tmpl::searchBug);

        // === User & Setting Tests ===
        System.out.println("\n--- User & Setting ---");
        UserSettingTest usr = new UserSettingTest();
        run("TC-USER-07 no status BUG", usr::createUserNoStatusBug);
        run("TC-SET-02 empty name", usr::settingEmptyName);
        run("TC-SET-06 pwd mismatch", usr::passwordMismatch);
        run("TC-SET-08 same pwd BUG", usr::samePasswordBug);

        // === Summary ===
        System.out.println("\n===========================");
        System.out.println("Test Summary");
        System.out.println("Total:  " + total);
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Rate:   " + (passed * 100 / total) + "%");
        System.out.println("===========================");

        Utils.quit();
    }
}
