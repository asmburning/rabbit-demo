# If you come from bash you might have to change your $PATH.
export PATH=$HOME/bin:/usr/local/bin:/Users/liuxinyi/Downloads/apache-maven-3.5.4/bin:$PATH

# Path to your oh-my-zsh installation.
export ZSH="/Users/liuxinyi/.oh-my-zsh"

# Set name of the theme to load --- if set to "random", it will
# load a random theme each time oh-my-zsh is loaded, in which case,
# to know which specific one was loaded, run: echo $RANDOM_THEME
# See https://github.com/robbyrussell/oh-my-zsh/wiki/Themes
ZSH_THEME="robbyrussell"

# Set list of themes to pick from when loading at random
# Setting this variable when ZSH_THEME=random will cause zsh to load
# a theme from this variable instead of looking in ~/.oh-my-zsh/themes/
# If set to an empty array, this variable will have no effect.
# ZSH_THEME_RANDOM_CANDIDATES=( "robbyrussell" "agnoster" )

# Uncomment the following line to use case-sensitive completion.
# CASE_SENSITIVE="true"

# Uncomment the following line to use hyphen-insensitive completion.
# Case-sensitive completion must be off. _ and - will be interchangeable.
# HYPHEN_INSENSITIVE="true"

# Uncomment the following line to disable bi-weekly auto-update checks.
# DISABLE_AUTO_UPDATE="true"

# Uncomment the following line to automatically update without prompting.
# DISABLE_UPDATE_PROMPT="true"

# Uncomment the following line to change how often to auto-update (in days).
# export UPDATE_ZSH_DAYS=13

# Uncomment the following line if pasting URLs and other text is messed up.
# DISABLE_MAGIC_FUNCTIONS=true

# Uncomment the following line to disable colors in ls.
# DISABLE_LS_COLORS="true"

# Uncomment the following line to disable auto-setting terminal title.
# DISABLE_AUTO_TITLE="true"

# Uncomment the following line to enable command auto-correction.
# ENABLE_CORRECTION="true"

# Uncomment the following line to display red dots whilst waiting for completion.
# COMPLETION_WAITING_DOTS="true"

# Uncomment the following line if you want to disable marking untracked files
# under VCS as dirty. This makes repository status check for large repositories
# much, much faster.
# DISABLE_UNTRACKED_FILES_DIRTY="true"

# Uncomment the following line if you want to change the command execution time
# stamp shown in the history command output.
# You can set one of the optional three formats:
# "mm/dd/yyyy"|"dd.mm.yyyy"|"yyyy-mm-dd"
# or set a custom format using the strftime function format specifications,
# see 'man strftime' for details.
# HIST_STAMPS="mm/dd/yyyy"

# Would you like to use another custom folder than $ZSH/custom?
# ZSH_CUSTOM=/path/to/new-custom-folder

# Which plugins would you like to load?
# Standard plugins can be found in ~/.oh-my-zsh/plugins/*
# Custom plugins may be added to ~/.oh-my-zsh/custom/plugins/
# Example format: plugins=(rails git textmate ruby lighthouse)
# Add wisely, as too many plugins slow down shell startup.
plugins=(zsh-autosuggestions)

source $ZSH/oh-my-zsh.sh

# User configuration

# export MANPATH="/usr/local/man:$MANPATH"

# You may need to manually set your language environment
# export LANG=en_US.UTF-8

# Preferred editor for local and remote sessions
# if [[ -n $SSH_CONNECTION ]]; then
#   export EDITOR='vim'
# else
#   export EDITOR='mvim'
# fi

# Compilation flags
# export ARCHFLAGS="-arch x86_64"

# Set personal aliases, overriding those provided by oh-my-zsh libs,
# plugins, and themes. Aliases can be placed here, though oh-my-zsh
# users are encouraged to define aliases within the ZSH_CUSTOM folder.
# For a full list of active aliases, run `alias`.
#
# Example aliases
# alias zshconfig="mate ~/.zshrc"
# alias ohmyzsh="mate ~/.oh-my-zsh"


alias szs="source /Users/liuxinyi/.zshrc"
#alias vzs="vim /Users/liuxinyi/.zshrc"
alias vzs="open -a 'Atom' /Users/liuxinyi/.zshrc"
alias vbs="open -a 'Atom' /Users/liuxinyi/.bashrc"
alias vhs="sudo vim /etc/hosts"

alias atom="open -a 'Atom' "

alias lg="sh /Users/liuxinyi/lg.sh"
alias m2="sh /Users/liuxinyi/m2.sh"

alias pjson="python -m json.tool"

alias pip3="/Users/liuxinyi/anaconda3/bin/pip"
alias python3="/Users/liuxinyi/anaconda3/bin/python"

alias mvn36="/Users/liuxinyi/Downloads/apache-maven-3.6.0/bin/mvn"
alias mvnd="mvn dependency:resolve -Dclassifier=sources"

alias dbv="open -a 'Google Chrome' http://dbarchery.hellobike.cn/queryapplylist/"
alias crp="open -a 'Google Chrome' https://sso.hellobike.cn"
alias weather="python3 /Users/liuxinyi/PycharmProjects/RentBikeAutoTest/xxx/weather.py"

alias gitam="git commit -am "

alias rabbitmq-server="sh /usr/local/Cellar/rabbitmq/3.7.14/sbin/rabbitmq-server"

# 查询Java 版本和目录
# /usr/libexec/java_home -V
# JDK 11
export JAVA_11_HOME="/Library/Java/JavaVirtualMachines/jdk-11.0.3.jdk/Contents/Home"
# JDK 8
export JAVA_8_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home"

export JAVA_HOME=$JAVA_8_HOME #默认JDK 8

#alias命令动态切换JDK版本
alias jdk8="export JAVA_HOME=$JAVA_8_HOME"
alias jdk11="export JAVA_HOME=$JAVA_11_HOME"
#10分钟后关机 sudo shutdown -h +10
#晚上8点关机 sudo shutdown -h 20:00
#立即重启 sudo reboot
alias sd=" echo ' +10 in 10 minutes , 21:00 on 21:00 '; sudo shutdown -h"

alias gb='echo $(git branch) '

alias vds='python /Users/liuxinyi/venus_ds.py'

alias finance='echo -e "
\e[4;32m devs: 3081  fat:3773  \e[0m
\e[4;33m uat:04679  pt:04698   \e[0m
\e[4;36m pro:04630 04631 04632 04633  \e[0m" ;
echo "cd /workspace/carkey/AppRentFinanceService/latest/logs" | pbcopy;lg'

alias user='echo -e "
\e[4;32m dev:04146  fat:04414  \e[0m
\e[4;33m uat:04679  pt:04698   \e[0m
\e[4;36m pro:04630 04631 04632 04633  \e[0m" ;
echo "cd /workspace/carkey/AppRentUserService/latest/logs" | pbcopy;lg'

alias order='echo -e "
\e[4;32m dev:03081  03888  fat: 3772 \e[0m
\e[4;33m uat:3774 3775  pt:3091   \e[0m
\e[4;36m pro:1606 1607 2725 2726  \e[0m" ;
echo "cd /workspace/carkey/AppRentOrderService/latest/logs" | pbcopy;lg'

alias bikeM="echo 'cd /workspace/carkey/AppRentBikeManagerService/latest/logs' | pbcopy;lg"
alias activity="echo 'cd /workspace/carkey/AppRentActivityService/latest/logs' | pbcopy;lg"

alias activity='echo -e "
\e[4;32m devs:03888  dev2:03081  \e[0m
\e[4;33m fat1:03772 03773  fat2:02591   \e[0m
\e[4;33m uat1 :03774 03775 uat2:02851  pt:03211   \e[0m
\e[4;36m pro2:02726 02725 pro1 01325 01326  \e[0m" ;
echo "cd /workspace/carkey/AppRentActivityService/latest/logs" | pbcopy;lg'

alias baseData='echo -e "
\e[4;32m dev:04146  fat:04414  \e[0m
\e[4;33m uat:04679  pt:04698   \e[0m
\e[4;36m pro:04630 04631 04632 04633  \e[0m" ;
echo "cd /workspace/carkey/AppRentBaseDataService/latest/logs" | pbcopy;lg'

alias adminApi='echo -e "
\e[4;32m devs:03885 dev2:03081  \e[0m
\e[4;32m fat1:03772 03773 fat2:02592  \e[0m
\e[4;33m uat:04679  pt:04698   \e[0m
\e[4;36m pro1:02444 02445 pro2: 02865 02866  \e[0m" ;
echo "cd /workspace/carkey/AppRentAdminApi/latest/logs" | pbcopy;lg'

alias userApi='echo -e "
\e[4;32m dev:3885  3081  fat1:3772 3773  \e[0m
\e[4;33m uat:3774 3775  pt:3091   \e[0m
\e[4;36m pro1:2440 2441 pro2:2865 2866  \e[0m" ;
echo "cd /workspace/carkey/AppRentUserApi/latest/logs" | pbcopy;lg'

alias userData='echo -e "
\e[4;32m dev:04146  fat:04414  \e[0m
\e[4;33m uat:04679  pt:04698   \e[0m
\e[4;36m pro:04630 04631 04632 04633  \e[0m" ;
echo "cd /workspace/carkey/AppRentUserDataService/latest/logs" | pbcopy;lg'

alias upo='echo -e "
\e[4;32m dev:04146  fat:04414  \e[0m
\e[4;33m uat:04679  pt:04698   \e[0m
\e[4;36m pro:04630 04631 04632 04633  \e[0m" ;
echo "cd /workspace/carkey/AppRentUPOService/latest/logs" | pbcopy;lg'

alias gerrit='echo -e "
\e[4;32m 01891 \e[0m
\e[4;33m sudo su - deploy \e[0m
\e[4;36m cd /etc/httpd \e[0m
\e[4;36m sudo htpasswd passwords liuxinyi05972 \e[0m";
echo "sudo htpasswd passwords liuxinyi05972" | pbcopy;lg'

alias venuscommon='echo -e "
\e[4;32m dev:04146  fat:04414  \e[0m
\e[4;33m uat:04679  pt:04698   \e[0m
\e[4;36m pro:04630 04631 04632 04633  \e[0m" ;
echo "cd /workspace/carkey/AppHelloVenusCommonService/latest/logs" | pbcopy;lg'

alias venusmaster='echo -e "
\e[4;32m dev:04145  fat:04412   \e[0m
\e[4;33m uat:04678  pt:04698   \e[0m
\e[4;36m pro:04634 04635  \e[0m";
echo "cd /workspace/carkey/AppHelloVenusMaster/latest/logs" | pbcopy;lg'

alias venusapi='echo -e "
\e[4;32m dev:04146  fat:04414  \e[0m
\e[4;33m uat:04679   pt:04698   \e[0m
\e[4;36m pro:04626 04627 04628 04629  \e[0m";
echo "cd /workspace/carkey/AppHelloVenusApi/latest/logs" | pbcopy;lg'


alias gerritpush='cb=$(git branch|grep \*|head -n 1);
b=${cb:2};echo $b;
git push origin HEAD:refs/for/$b'

gerritpush2(){
  # before push first pull
  echo "before push first pull"
  upToDate=$(git pull|grep 'Already up to date.')
  if [ ${#upToDate} -gt 0 ]
  then
    echo "Already up to date. do gerritpush "
    gerritpush
  else
    echo "need work , abort gerritpush"
  fi
}

alias gerritflow='echo -e "
\e[4;31m git add git commit \e[0m
\e[4;31m git pull --rebase \e[0m
\e[4;32m resolve confict \e[0m
\e[4;33m mark resolved \e[0m
\e[4;34m git rebase --continue \e[0m
\e[4;35m 没有push之前追加提交时 git commit --amend \e[0m
\e[4;35m 合并分支 1 git rebase feature/sprint-w30 2 git commit 3 gerritpush \e[0m
\e[4;36m gerritpush \e[0m"'

ggb(){
  echo $(git branch | grep $1)
}

ggrb(){
  echo $(git branch -r | grep $1)
}

# checkout wechat$
checkout(){
  git fetch
  rb=$(git branch -r | grep $1);
  b=${rb:9}
  echo -e "\e[4;31m find branch :$b \e[0m";
  echo -e "\e[4;37m if you want to checkout ,please type yes. \e[0m";
  read check;
  if [ "$check" = "yes" ]
  then
    git checkout $b;
    git pull;
  else
    echo "abort checkout!"
  fi
}

# https://www.tldp.org/LDP/abs/html/intandnonint.html
testRead(){
  MY_PROMPT='$ '
while :
do
  echo -n "$MY_PROMPT"
  read line
  eval "$line"
  done

exit 0
}


testReply(){
# REPLY is the default value for a 'read' command.
echo
echo -n "What is your favorite vegetable? "
read -t 3

echo "Your favorite vegetable is $REPLY."
#  REPLY holds the value of last "read" if and only if
#+ no variable supplied.

echo
echo -n "What is your favorite fruit? "
read fruit
echo "Your favorite fruit is $fruit."
echo "but..."
echo "Value of \$REPLY is still $REPLY."
#  $REPLY is still set to its previous value because
#+ the variable $fruit absorbed the new "read" value.

echo -n "What is your favorite sport? "
read
echo "Your favorite sport is $REPLY."
echo "but..."
echo "Value of \$REPLY is now $REPLY."

echo $SECONDS

}

testIsRoot(){

ROOT_UID=0   # Root has $UID 0.

if [ "$UID" -eq "$ROOT_UID" ]  # Will the real "root" please stand up?
then
  echo "You are root."
else
  echo "You are just an ordinary user (but mom loves you just the same)."
fi
}

#change file or directory permission
#sudo chmod -R 755 ~/.oh-my-zsh
mychmod(){
  sudo chmod -R 755 $1
}

#change file or directory owner
#sudo chown -R $USER ~/.oh-my-zsh
mychown(){
  echo $1
  sudo chown -R $USER $1
}

alias stashFlow='echo -e "
\e[4;31m git stash \e[0m
\e[4;31m git pull \e[0m
\e[4;32m git stash apply \e[0m
\e[4;33m resolved conflict if need \e[0m
\e[4;34m git commit -am msg \e[0m
\e[4;36m gerritpush \e[0m"'

#just test sync

alias gp="git pull"
alias gam="git commit -am "
alias gs="git status"

g(){
  open -a 'Google Chrome' "https://www.google.com/search?q=$1"
}

alias pdf="open -a 'Adobe Acrobat Reader DC' "
alias preview="open -a 'Preview' "

gbd(){
  # local  : git branch -d branchName
  # remote : git push origin --delete <BranchName>
  # delete local branches except current branch and master
  echo "begin delete local branches"
  cb=$(git branch|grep \*|head -n 1)
  b=${cb:2}
  echo "current branch $b"
  git branch | tr -d " *"  | while read line ; do
    echo $line;
    if [ $line = "master" ]; then
      echo -e "\e[4;31m skip $line \e[0m"
    elif [ $line = $b ]; then
      echo -e "\e[4;32m skip $line \e[0m"
    else
      echo -e "\e[4;36m delete $line \e[0m"
      git branch -d $line
    fi
  done
}

######## test ln ######
syncZshrc(){
  cd /Users/liuxinyi/Documents/code/GitHub/rabbit-demo1
  git pull
  gam 'sync'
  git push
  cd
}
